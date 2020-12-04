package com.aoc2020

import java.io.File

class PassportValidator {
    fun countValidPassports(file: File, optionalFields: List<String>): Int {
        val passports = parsePassportFile(file)
        val ret = countValidPassport(passports, optionalFields)
        return ret
    }

    fun countValidPassportsComplex(file: File, optionalFields: List<String>): Int {
        var ret = 0
        val passports = parsePassportFile(file)
        ret = countValidPassportComplex(passports, optionalFields)
        return ret
    }
}

val byrRange = 1920..2002
val iyrRange = 2010..2020
val eyrRange = 2020..2030
val hgtRe = "^(\\d+)(cm|in)$".toRegex()
val hclRe = "^#[a-f0-9]{6}\$".toRegex()
val eclValids = listOf<String>("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
val pidRe = "^[0-9]{9}$".toRegex()

private fun countValidPassportComplex(passports: List<Passport>, optionalFields: List<String>): Int {
    var ret = 0
    var isValid: Boolean
    passports.forEach { it ->
        isValid = true
        // part 1 rules
        if (it.ecl == null && !optionalFields.contains("ecl")) isValid = false

        if (it.pid == null && !optionalFields.contains("pid")) isValid = false
        if (it.eyr == null && !optionalFields.contains("eyr")) isValid = false
        if (it.hcl == null && !optionalFields.contains("hcl")) isValid = false
        if (it.byr == null && !optionalFields.contains("byr")) isValid = false
        if (it.iyr == null && !optionalFields.contains("iyr")) isValid = false
        if (it.cid == null && !optionalFields.contains("cid")) isValid = false
        if (it.hgt == null && !optionalFields.contains("hgt")) isValid = false
        if (isValid) {
            if (it.byr!!.toInt() !in byrRange) isValid = false
            if (it.iyr!!.toInt() !in iyrRange) isValid = false
            if (it.eyr!!.toInt() !in eyrRange) isValid = false
            if (!hgtValid(it.hgt)) isValid = false
            if (it.hcl?.matches(hclRe) == false) isValid = false
            if (it.ecl !in eclValids) isValid = false
            if (it.pid?.matches(pidRe) == false) isValid = false
        }
        if (isValid) ret++
    }
    return ret
}

private fun countValidPassport(passports: List<Passport>, optionalFields: List<String>): Int {
    var ret = 0
    passports.forEach { it ->
        // part 1 rules
        if ((it.ecl != null || optionalFields.contains("ecl")) &&
                (it.pid != null || optionalFields.contains("pid")) &&
                (it.eyr != null || optionalFields.contains("eyr")) &&
                (it.hcl != null || optionalFields.contains("hcl")) &&
                (it.byr != null || optionalFields.contains("byr")) &&
                (it.iyr != null || optionalFields.contains("iyr")) &&
                (it.cid != null || optionalFields.contains("cid")) &&
                (it.hgt != null || optionalFields.contains("hgt"))
        ) {
            ret++
        }
    }
    return ret
}

private fun hgtValid(hgt: String? = ""): Boolean {
    val cmRange = 150..193
    val inRange = 59..76
    var ret = false

    val matches = hgtRe.find(hgt.toString())
    val num = matches?.groups?.get(1)?.value
    val unit = matches?.groups?.get(2)?.value
    if (unit == "cm" && num?.toInt() in cmRange) ret = true
    if (unit == "in" && num?.toInt() in inRange) ret = true

    return ret
}

private fun parsePassportFile(file: File): List<Passport> {
    val ret = mutableListOf<Passport>()
    val data = file.readText(Charsets.UTF_8)
    val tokens = data.split(":", " ", "\r\n")

    var ecl: String? = null
    var pid: String? = null
    var eyr: String? = null
    var hcl: String? = null
    var byr: String? = null
    var iyr: String? = null
    var cid: String? = null
    var hgt: String? = null

    var i = 0
    while (i < tokens.size) {

        when (tokens.get(i)) {
            "ecl" -> {
                ecl = tokens.get(i + 1); i++
            }
            "pid" -> {
                pid = tokens.get(i + 1); i++
            }
            "eyr" -> {
                eyr = tokens.get(i + 1); i++
            }
            "hcl" -> {
                hcl = tokens.get(i + 1); i++
            }
            "byr" -> {
                byr = tokens.get(i + 1); i++
            }
            "iyr" -> {
                iyr = tokens.get(i + 1); i++
            }
            "cid" -> {
                cid = tokens.get(i + 1); i++
            }
            "hgt" -> {
                hgt = tokens.get(i + 1); i++
            }
            "" -> { // end of passport
                ret.add(Passport(ecl, pid, eyr, hcl, byr, iyr, cid, hgt))
                ecl = null; pid = null; eyr = null;hcl = null
                byr = null; iyr = null; cid = null; hgt = null
            }
        }
        i++
    }
    println("Found ${ret.size} passports")
    return ret
}

data class Passport(
        val ecl: String?,
        val pid: String?,
        val eyr: String?,
        val hcl: String?,
        val byr: String?,
        val iyr: String?,
        val cid: String?,
        val hgt: String?
)
