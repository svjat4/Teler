package com.kampus.teler.repository

import android.content.Context
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TextClasifierAlergiHelper(context: Context, private val modelPath:String) {
    private val tfliteInterpreter: Interpreter

    private val inputShape: IntArray
    private val outputShape: IntArray

    init {
        val tfliteModel = FileUtil.loadMappedFile(context, modelPath)
        val tfliteOptions = Interpreter.Options()
        tfliteInterpreter = Interpreter(tfliteModel, tfliteOptions)

        inputShape = tfliteInterpreter.getInputTensor(0).shape()
        outputShape = tfliteInterpreter.getOutputTensor(0).shape()
    }

    fun classifyText(inputData: ArrayList<Float>): ArrayList<Float> {
        val inputBuffer = getInputBuffer(inputData)
        val outputBuffer = getOutputBuffer()

        tfliteInterpreter.run(inputBuffer, outputBuffer)
        println("data classifyText outputBuffer in helper ml 38 $outputBuffer")
        println("data classifyText inputBuffer in helper ml 39 $inputBuffer")
        println("data classifyText result in helper ml 40")
        println(getOutputLabel(outputBuffer))
        return getOutputData(outputBuffer)
    }

    private fun getInputBuffer(inputData: ArrayList<Float>): ByteBuffer {
        val inputBuffer = ByteBuffer.allocateDirect(inputShape[0] * inputShape[1] * 4)
            .order(ByteOrder.nativeOrder())
//            .asFloatBuffer()
        val byteBufferView = inputBuffer.asFloatBuffer()

        for (i in inputData.indices) {
            byteBufferView.put(inputData[i])
        }
        inputBuffer.rewind()
        println("data inputBuffer in helper ml 52 $inputBuffer")
        println("data inputData in helper ml 53 $inputData")
        return inputBuffer
    }

    private fun getOutputBuffer(): ByteBuffer {
        val outputBuffer = ByteBuffer.allocateDirect(outputShape[0] * outputShape[1] * 4)
            .order(ByteOrder.nativeOrder())
//            .asFloatBuffer()
//        val byteBufferView = outputBuffer.asFloatBuffer()
        println("data getOutputBuffer in helper ml 61 $outputBuffer")
        return  outputBuffer
    }

    private fun getOutputLabel(outputBuffer: ByteBuffer): String {
        val outputLabels = mutableListOf<String>()

        outputBuffer.rewind()
        for (i in 0 until outputShape[0]) {
            val labelBytes = ByteArray(outputShape[1] * 4)
            outputBuffer.get(labelBytes)
            val label = String(labelBytes)
            println("data labelBytes in helper ml ${labelBytes}")
            outputLabels.add(label)
        }
        println("data outputBuffer in helper ml ${outputBuffer.toString()}")
        println("data getOutputLabel in helper ml ${outputLabels.toString()}")
        return outputLabels.toString() //.maxByOrNull { it } ?: "Unknown"
    }

    private fun getOutputData(outputBuffer: ByteBuffer): ArrayList<Float> {
        val outputData = ArrayList<Float>()

        outputBuffer.rewind()
        for (i in 0 until outputShape[0]) {
            outputData.add(outputBuffer.float)
        }

        return outputData
    }

//    fun getLabel(outputArray: FloatArray): String {
//        val maxIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
//        return labelMap[maxIndex] ?: "Unknown"
//    }
}
