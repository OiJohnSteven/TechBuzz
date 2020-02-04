package com.techbuzz

class Person {
    var id:Int
    var description:String
    var field:String
    var image:String

    constructor(id:Int,
                description:String,field:String,
                image:String){
        this.id = id
        this.field = field
        this.description = description
        this.image = image
    }
}