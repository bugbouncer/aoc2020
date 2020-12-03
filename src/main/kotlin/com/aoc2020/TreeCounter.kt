package com.aoc2020

class TreeCounter {
    fun countTrees(input : List<String>, stepDown : Int = 1, stepRight : Int = 3) : Int {
        var ret = 0
        var posx = 0
        var line = ""
        for(i in stepDown until input.size step stepDown) {
            posx += stepRight
            line = input.get(i)
            while(line.length <= posx) line = line + line
            if(line.get(posx).equals('#')) ret++
        }
        return ret
    }
}
