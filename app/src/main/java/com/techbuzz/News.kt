package com.techbuzz

class News {
    var id:Int
    var author:String
    var description:String
    var image:String

    constructor(id:Int,author:String,
                description:String,
                image:String){
        this.id = id
        this.author = author
        this.description = description
        this.image = image
    }
}