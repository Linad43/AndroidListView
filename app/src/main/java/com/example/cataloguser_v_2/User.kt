package com.example.cataloguser_v_2

data class User(
    val name: String,
    val age: Int
) {
    override fun toString(): String {
        var result = "$name ($age "
        result += if (age % 10 == 1 && age !in 10..20) {
            "год)"
        } else if (age % 10 in 2..4 && age !in 10..20) {
            "года)"
        } else {
            "лет)"
        }
        return result
    }
}