package xyz.treelar.lovelivehdurserver.assemblers

import org.slf4j.LoggerFactory
import xyz.treelar.lovelivehdurserver.data.Card
import xyz.treelar.lovelivehdurserver.data.CardResponse
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import javax.imageio.ImageIO


open class NormalCard(cr: CardResponse?, idolized: Boolean, scale: Float?): Waifu2xable
{
    protected var cardUrl: String?
    lateinit var img: BufferedImage
    protected lateinit var card: Card
    private val scale = scale ?: 1f

    final override var inPath: Path
    final override var outPath: Path

    val logger = LoggerFactory.getLogger(NormalCard::class.java)

    init {
        if (cr != null) {
            getValidCardFromList(cr)
        }
        cardUrl = if(idolized) card.cleanUrIdolized else card.cleanUr

        require(!cardUrl.isNullOrBlank()) {"No Card Images Found"}

        inPath = Paths.get("./cardsIn/${card.id}-$idolized.png").toAbsolutePath().normalize()
        outPath = Paths.get("./cardsOut/${card.id}-$idolized.jpg").toAbsolutePath().normalize()
    }

    private fun getValidCardFromList(cr: CardResponse) {
        require(cr.results?.size!! > 0) {"No Cards Found"}

        val trialCard = cr.results.random()

        return if (trialCard.cleanUr != null)
            card = trialCard
        else
            getValidCardFromList(cr)
    }

    override fun downloadImage()
    {
        img = ImageIO.read(URL("https:$cardUrl"))
    }

    override fun writeImage()
    {
        ImageIO.write(img, "png", inPath.toFile().outputStream())
    }

    override fun waifu2xIfy()
    {
        if (!inPath.toFile().exists())
        {
            downloadImage()
            writeImage()
        }

        if(!outPath.toFile().exists())
        {
            ProcessBuilder("/usr/bin/waifu2x-converter-cpp", "-i $inPath", "-o $outPath", "--scale-ratio $scale", "--noise-level 3")
                    .inheritIO()
                    .start()
                    .waitFor()
        }

        img = ImageIO.read(outPath.toFile().inputStream())
    }
}