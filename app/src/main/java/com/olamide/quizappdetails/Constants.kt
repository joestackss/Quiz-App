package com.olamide.quizappdetails

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions (): ArrayList <Question> {
        val questionList = ArrayList<Question>()

        // 1
        val que1 = Question (
            1, "Which city in Ukraine has the largest port?",
            R.drawable.port ,
            optionOne = "Kyiz", "Mariopol", optionThree = "Lyiv",
            correctAnswer = 2
                )
        questionList.add(que1)
1
        // 2
        val que2 = Question (
            1, "Which country has the best military?",
            R.drawable.military,
            optionOne = "Russia", "China", optionThree = "USA",
            correctAnswer = 2
        )
        questionList.add(que2)


        //3

        val que3 = Question (
            1, "Which African City is the most developed?",
            R.drawable.city,
            optionOne = "Cairo", "Cape Town", optionThree = "Lagos",
            correctAnswer = 1
        )
        questionList.add(que3)


        return questionList
    }
}