package com.kampus.teler

import com.kampus.teler.model.TestQuetions

object Constants {

    fun getTestQuetion(): ArrayList<TestQuetions>{
        val questionsTestList = ArrayList<TestQuetions>()
        // 1
        val questionOne = TestQuetions(
            1,
            "Apakah kulit anda terasa gatal?",
            arrayListOf("Ya","Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionOne)

        // 2
        val questionTwo = TestQuetions(
            2,
            "Apakah Anda melihat adanya ruam atau perubahan warna kulit yang memerah?",
            arrayListOf("Ya","Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionTwo)

        // 3
        val questionThree = TestQuetions(
            3,
            "Apakah Anda mengalami pembengkakan di bagian wajah?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionThree)

        // 4
        val questionFour = TestQuetions(
            4,
            "Apakah terdapat pembengkakan pada bibir Anda?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionFour)

        // 5
        val questionFive = TestQuetions(
            5,
            "Apakah Anda merasakan gatal di dalam mulut atau sekitar bibir?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionFive)

        // 6
        val questionSix = TestQuetions(
            6,
            "Apakah Anda merasakan kesemutan atau mati rasa di area mulut Anda?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionSix)

        // 7
        val questionSeven = TestQuetions(
            7,
            "Apakah Anda merasakan gatal di sekitar mata Anda?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionSeven)

        // 8
        val questionEight = TestQuetions(
            8,
            "Apakah terdapat pembengkakan di area sekitar mata Anda?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionEight)

        // 9
        val questionNine = TestQuetions(
            9,
            "Apakah mata Anda terlihat merah?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionNine)

        // 10
        val questionTen = TestQuetions(
            10,
            "Apakah sering kali Anda bersin?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionTen)

        // 11
        val questionEleven = TestQuetions(
            11,
            "Apakah hidung Anda terasa tersumbat?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionEleven)

        // 12
        val questionTwelve = TestQuetions(
            12,
            "Apakah Anda sedang mengalami batuk?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionTwelve)

        // 13
        val questionThirteen = TestQuetions(
            13,
            "Apakah Anda mengalami sesak napas?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionThirteen)

        // 14
        val questionFourteen = TestQuetions(
            14,
            "Apakah Anda sedang pilek?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionFourteen)

        // 15
        val questionFiveteen = TestQuetions(
            15,
            "Apakah Anda merasakan sakit perut?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionFiveteen)

        // 16
        val questionSixteen = TestQuetions(
            16,
            "Apakah Anda merasa mual?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionSixteen)

        // 17
        val questionSeventeen = TestQuetions(
            17,
            "Apakah Anda mengalami muntah-muntah?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionSeventeen)

        // 18
        val questionEighteen = TestQuetions(
            18,
            "Apakah Anda mengalami diare?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionEighteen)

        // 19
        val questionNineteen = TestQuetions(
            19,
            "Apakah gejala-gejala ini muncul kembali pada musim tertentu?",
            arrayListOf("Ya", "Tidak"),
            arrayListOf(),
        )
        questionsTestList.add(questionNineteen)

        return  questionsTestList
    }
}