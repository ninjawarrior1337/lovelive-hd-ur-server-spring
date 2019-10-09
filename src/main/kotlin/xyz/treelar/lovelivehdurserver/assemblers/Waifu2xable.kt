package xyz.treelar.lovelivehdurserver.assemblers

import java.nio.file.Path

interface Waifu2xable
{
    val inPath: Path
    val outPath: Path

    fun writeImage()
    fun downloadImage()

    fun waifu2xIfy()
}