package xyz.treelar.lovelivehdurserver.assemblers

import xyz.treelar.lovelivehdurserver.data.Card
import xyz.treelar.lovelivehdurserver.data.CardResponse
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.net.URL
import java.nio.file.Paths
import javax.imageio.ImageIO

class UrPair(cr: CardResponse?, private val idolized: Boolean, scale: Int?) : NormalCard(cr, idolized, scale), Waifu2xable {

    private val pairCard: Card
    private var pairCardUrl: String?
    lateinit var pairImg: BufferedImage

    var orderedCards: List<Card>

    init {
        require(card.urPair?.card != null) {"Selected card doesn't have a UR Pair"}
        pairCard = card.urPair?.card!!
        pairCardUrl = if(idolized) pairCard.cleanUrIdolized else pairCard.cleanUr

        orderedCards = arrayListOf(card, pairCard).sortedBy { it.id }

        inPath = Paths.get("./cardsIn/${orderedCards[0].id}x${orderedCards[1].id}-$idolized.png").toAbsolutePath().normalize()
        outPath = Paths.get("./cardsOut/${orderedCards[0].id}x${orderedCards[1].id}-$idolized.jpg").toAbsolutePath().normalize()
    }

    override fun downloadImage()
    {
        println(listOf(cardUrl, pairCardUrl))
        img = ImageIO.read(URL("https:$cardUrl"))
        pairImg = ImageIO.read(URL("https:$pairCardUrl"))
    }

    override fun writeImage() {
        if(inPath.toFile().exists())
            return
        var completeImage: BufferedImage? = null
        when(idolized)
        {
            true -> when(card.urPair?.reverseDisplay)
            {
                false -> completeImage = stitchImages(img, pairImg)
                true -> completeImage = stitchImages(pairImg, img)
            }
            false -> when(card.urPair?.reverseDisplayIdolized)
            {
                false -> completeImage = stitchImages(img, pairImg)
                true -> completeImage = stitchImages(pairImg, img)
            }
        }
        require(completeImage != null) {"What just happened"}
        ImageIO.write(completeImage, "png", inPath.toFile())
    }

    private fun stitchImages(leftImg: BufferedImage, rightImg: BufferedImage): BufferedImage {
        val stichedImage = BufferedImage(leftImg.width + rightImg.width, leftImg.height, BufferedImage.TYPE_INT_ARGB)

        val g2 = stichedImage.graphics as Graphics2D

        g2.drawImage(leftImg, 0, 0, null)
        g2.drawImage(rightImg, leftImg.width, 0, null)

        return stichedImage
    }
}