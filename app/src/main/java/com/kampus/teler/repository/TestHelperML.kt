package com.kampus.teler.repository

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TestHelperML (private val context: Context){
    private lateinit var interpreter: Interpreter
    private lateinit var labelList: List<String>
    private var inputSize: Int = 0

    fun loadModel(modelPath: String, labelPath: String) {
        val tfliteOptions = Interpreter.Options()
        interpreter = Interpreter(loadModelFile(modelPath), tfliteOptions)
        labelList = loadLabelList(labelPath)
        inputSize = interpreter.getInputTensor(0).shape()[1]
    }

    private fun loadModelFile(modelPath: String): MappedByteBuffer {
        val assetManager = context.assets
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun loadLabelList(labelPath: String): List<String> {
        return context.assets.open(labelPath).bufferedReader().useLines { it.toList() }
    }

    fun classifyPredictedALergy(inputData: ArrayList<Float>): Map<String, Float> {
        require(inputData.size == inputSize) { "Jumlah elemen input tidak sesuai dengan model TFLite." }

        val inputArray = inputData.toFloatArray()
        val outputArray = Array(1) { FloatArray(labelList.size) }

        interpreter.run(inputArray, outputArray)

        val resultMap = mutableMapOf<String, Float>()
        for (i in labelList.indices) {
            resultMap["Alergi ${labelList[i]}"] = outputArray[0][i]
        }

        return resultMap
    }


}