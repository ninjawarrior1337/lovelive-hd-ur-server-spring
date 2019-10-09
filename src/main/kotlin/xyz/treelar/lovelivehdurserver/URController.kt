package xyz.treelar.lovelivehdurserver

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.util.UriComponentsBuilder
import sun.net.www.content.text.Generic
import xyz.treelar.lovelivehdurserver.assemblers.GenericImage
import xyz.treelar.lovelivehdurserver.assemblers.NormalCard
import xyz.treelar.lovelivehdurserver.assemblers.UrPair
import xyz.treelar.lovelivehdurserver.data.CardResponse
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class URController
{
    @GetMapping("/", produces = ["image/png"])
    fun cards(
            @RequestParam(required=false, defaultValue = "", name = "id") ids: String,
            @RequestParam(required=false, defaultValue = "") search: String,
            @RequestParam(required=false, defaultValue = "true") idolized: Boolean,
            @RequestParam(required=false, defaultValue = "1") scale: Int,
            @RequestParam(required = false, defaultValue = "Otonokizaka Academy, Uranohoshi Girls' High School") school: String,
            @RequestParam(required = false, defaultValue = "false") urpair: Boolean,
            respRq: HttpServletResponse) {
        val restTemplate = RestTemplate()

        require(scale <= 4) {"Scale must be less than or equal to 4"}

        val builder = UriComponentsBuilder.fromHttpUrl("https://schoolido.lu/api/cards/")
        if(ids.isNotBlank())
            builder.queryParam("ids", ids)
        if(search.isNotBlank())
            builder.queryParam("search", search)
        builder.queryParam("school", school)
        builder.queryParam("ordering", "random")
        builder.queryParam("expand_ur_pair")
        builder.queryParam("rarity", "SSR,UR")
        builder.build()

        val resp: ResponseEntity<CardResponse> = restTemplate.getForEntity(builder.toUriString())

        when
        {
            !urpair -> {
                val nc = NormalCard(resp.body, idolized, scale)
                nc.waifu2xIfy()
                ImageIO.write(nc.img, "png", respRq.outputStream)
            }
            urpair -> {
                val uc = UrPair(resp.body, idolized, scale)
                uc.waifu2xIfy()
                ImageIO.write(uc.img, "png", respRq.outputStream)
            }
        }
    }

    @PostMapping("/img", produces = ["image/png"])
    fun img(
            file: MultipartFile,
            respRq: HttpServletResponse,
            @RequestParam(required = false, defaultValue = "1") scale: Int
    )
    {
        val img = ImageIO.read(file.inputStream)
        val genericImage = GenericImage(img, scale, file.inputStream)
        genericImage.waifu2xIfy()

        ImageIO.write(genericImage.img, "png", respRq.outputStream)
    }
}