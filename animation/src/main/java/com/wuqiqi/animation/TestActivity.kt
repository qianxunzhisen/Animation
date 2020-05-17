package com.wuqiqi.animation

fun main(){
    val s1:String="hello"
    val list:List<String> = listOf("apple","pear","banana")
    println(s1)
    println(s1.hellowworld())
    println(s1.capitalize())
    println(s1.reversed())
    println(s1.capitalEnd())
    println(list.maxBy { it.length })
    println(num1Andnum2(1,2,::plus))
    println(num1Andnum2(1,2,::minus))
    println(num1Andnum2(1,2,fun (num1: Int,num2: Int):Int{
        return (num1+num2)
    }))
    println(num1Andnum2(1,2,fun (num1: Int,num2: Int):Int{
        return num1-num2
    }))
    println(num1Andnum2(1,2){
        num1:Int,num2:Int -> num1+num2
    })
    println(num1Andnum2(1,2){
        num1,num2 -> num1+num2
    })
    println(num1Andnum2(1,2){
        num1,num2 -> num1+num2
    })
    println(list.findMax { it.length })
    println(list.findMax { it })
}

fun String.hellowworld(){
    println("helloworld")
}
fun plus(num1: Int,num2: Int):Int{
    return (num1+num2)
}

fun minus(num1: Int,num2: Int):Int{
    return num1-num2
}
//Unit 类似于java void
fun num1Andnum2(num1:Int,num2:Int,operat:(Int,Int)->Int):Int{
    return operat(num1,num2)
}

fun String.capitalEnd():String{
    if(this.isEmpty()) return ""
    val charArray:CharArray=this.toCharArray()
    charArray[length-1]=charArray[length-1].toUpperCase()
    return String(charArray)
}

fun <T,R:Comparable<R>> List<T>.findMax(block:(T)->R):T?{
    if(isEmpty()) return null;
    var maxElement:T = get(0)
    var maxValue:R = block(maxElement)
    for(element:T in this){
        var value:R = block(element)
        if(value>maxValue){
            maxElement = element
            maxValue = value
        }
    }
    return maxElement
}