// To parse the JSON, install jackson-module-kotlin and do:
//
//   val cardResponse = CardResponse.fromJson(jsonString)

package xyz.treelar.lovelivehdurserver.data

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.*
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.*

val mapper = jacksonObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
}

data class CardResponse (
        val count: Long? = null,
        val next: Any? = null,
        val previous: Any? = null,
        val results: List<Card>? = null
) {
        fun toJson() = mapper.writeValueAsString(this)

        companion object {
                fun fromJson(json: String) = mapper.readValue<CardResponse>(json)
        }
}

data class UrPair (
        @get:JsonProperty("reverse_display_idolized")@field:JsonProperty("reverse_display_idolized")
        val reverseDisplayIdolized: Boolean? = null,

        @get:JsonProperty("reverse_display")@field:JsonProperty("reverse_display")
        val reverseDisplay: Boolean? = null,

        val card: Card? = null
)

data class Card (
        val id: Long? = null,

        @get:JsonProperty("game_id")@field:JsonProperty("game_id")
        val gameID: Long? = null,

        val idol: Idol? = null,

        @get:JsonProperty("japanese_collection")@field:JsonProperty("japanese_collection")
        val japaneseCollection: String? = null,

        @get:JsonProperty("translated_collection")@field:JsonProperty("translated_collection")
        val translatedCollection: String? = null,

        val rarity: String? = null,
        val attribute: String? = null,

        @get:JsonProperty("japanese_attribute")@field:JsonProperty("japanese_attribute")
        val japaneseAttribute: String? = null,

        @get:JsonProperty("is_promo")@field:JsonProperty("is_promo")
        val isPromo: Boolean? = null,

        @get:JsonProperty("promo_item")@field:JsonProperty("promo_item")
        val promoItem: Any? = null,

        @get:JsonProperty("promo_link")@field:JsonProperty("promo_link")
        val promoLink: Any? = null,

        @get:JsonProperty("release_date")@field:JsonProperty("release_date")
        val releaseDate: String? = null,

        @get:JsonProperty("japan_only")@field:JsonProperty("japan_only")
        val japanOnly: Boolean? = null,

        val event: Any? = null,

        @get:JsonProperty("other_event")@field:JsonProperty("other_event")
        val otherEvent: Any? = null,

        @get:JsonProperty("is_special")@field:JsonProperty("is_special")
        val isSpecial: Boolean? = null,

        val hp: Long? = null,

        @get:JsonProperty("minimum_statistics_smile")@field:JsonProperty("minimum_statistics_smile")
        val minimumStatisticsSmile: Long? = null,

        @get:JsonProperty("minimum_statistics_pure")@field:JsonProperty("minimum_statistics_pure")
        val minimumStatisticsPure: Long? = null,

        @get:JsonProperty("minimum_statistics_cool")@field:JsonProperty("minimum_statistics_cool")
        val minimumStatisticsCool: Long? = null,

        @get:JsonProperty("non_idolized_maximum_statistics_smile")@field:JsonProperty("non_idolized_maximum_statistics_smile")
        val nonIdolizedMaximumStatisticsSmile: Long? = null,

        @get:JsonProperty("non_idolized_maximum_statistics_pure")@field:JsonProperty("non_idolized_maximum_statistics_pure")
        val nonIdolizedMaximumStatisticsPure: Long? = null,

        @get:JsonProperty("non_idolized_maximum_statistics_cool")@field:JsonProperty("non_idolized_maximum_statistics_cool")
        val nonIdolizedMaximumStatisticsCool: Long? = null,

        @get:JsonProperty("idolized_maximum_statistics_smile")@field:JsonProperty("idolized_maximum_statistics_smile")
        val idolizedMaximumStatisticsSmile: Long? = null,

        @get:JsonProperty("idolized_maximum_statistics_pure")@field:JsonProperty("idolized_maximum_statistics_pure")
        val idolizedMaximumStatisticsPure: Long? = null,

        @get:JsonProperty("idolized_maximum_statistics_cool")@field:JsonProperty("idolized_maximum_statistics_cool")
        val idolizedMaximumStatisticsCool: Long? = null,

        val skill: String? = null,

        @get:JsonProperty("japanese_skill")@field:JsonProperty("japanese_skill")
        val japaneseSkill: String? = null,

        @get:JsonProperty("skill_details")@field:JsonProperty("skill_details")
        val skillDetails: String? = null,

        @get:JsonProperty("japanese_skill_details")@field:JsonProperty("japanese_skill_details")
        val japaneseSkillDetails: Any? = null,

        @get:JsonProperty("center_skill")@field:JsonProperty("center_skill")
        val centerSkill: String? = null,

        @get:JsonProperty("center_skill_extra_type")@field:JsonProperty("center_skill_extra_type")
        val centerSkillExtraType: String? = null,

        @get:JsonProperty("center_skill_details")@field:JsonProperty("center_skill_details")
        val centerSkillDetails: String? = null,

        @get:JsonProperty("japanese_center_skill")@field:JsonProperty("japanese_center_skill")
        val japaneseCenterSkill: String? = null,

        @get:JsonProperty("japanese_center_skill_details")@field:JsonProperty("japanese_center_skill_details")
        val japaneseCenterSkillDetails: String? = null,

        @get:JsonProperty("card_image")@field:JsonProperty("card_image")
        val cardImage: String? = null,

        @get:JsonProperty("card_idolized_image")@field:JsonProperty("card_idolized_image")
        val cardIdolizedImage: String? = null,

        @get:JsonProperty("english_card_image")@field:JsonProperty("english_card_image")
        val englishCardImage: Any? = null,

        @get:JsonProperty("english_card_idolized_image")@field:JsonProperty("english_card_idolized_image")
        val englishCardIdolizedImage: Any? = null,

        @get:JsonProperty("round_card_image")@field:JsonProperty("round_card_image")
        val roundCardImage: String? = null,

        @get:JsonProperty("round_card_idolized_image")@field:JsonProperty("round_card_idolized_image")
        val roundCardIdolizedImage: String? = null,

        @get:JsonProperty("english_round_card_image")@field:JsonProperty("english_round_card_image")
        val englishRoundCardImage: Any? = null,

        @get:JsonProperty("english_round_card_idolized_image")@field:JsonProperty("english_round_card_idolized_image")
        val englishRoundCardIdolizedImage: Any? = null,

        @get:JsonProperty("video_story")@field:JsonProperty("video_story")
        val videoStory: Any? = null,

        @get:JsonProperty("japanese_video_story")@field:JsonProperty("japanese_video_story")
        val japaneseVideoStory: Any? = null,

        @get:JsonProperty("website_url")@field:JsonProperty("website_url")
        val websiteURL: String? = null,

        @get:JsonProperty("non_idolized_max_level")@field:JsonProperty("non_idolized_max_level")
        val nonIdolizedMaxLevel: Long? = null,

        @get:JsonProperty("idolized_max_level")@field:JsonProperty("idolized_max_level")
        val idolizedMaxLevel: Long? = null,

        @get:JsonProperty("transparent_image")@field:JsonProperty("transparent_image")
        val transparentImage: String? = null,

        @get:JsonProperty("transparent_idolized_image")@field:JsonProperty("transparent_idolized_image")
        val transparentIdolizedImage: String? = null,

        @get:JsonProperty("clean_ur")@field:JsonProperty("clean_ur")
        val cleanUr: String? = null,

        @get:JsonProperty("clean_ur_idolized")@field:JsonProperty("clean_ur_idolized")
        val cleanUrIdolized: String? = null,

        @get:JsonProperty("skill_up_cards")@field:JsonProperty("skill_up_cards")
        val skillUpCards: List<Any?>? = null,

        @get:JsonProperty("ur_pair")@field:JsonProperty("ur_pair")
        val urPair: UrPair? = null,

        @get:JsonProperty("total_owners")@field:JsonProperty("total_owners")
        val totalOwners: Long? = null,

        @get:JsonProperty("total_wishlist")@field:JsonProperty("total_wishlist")
        val totalWishlist: Long? = null,

        @get:JsonProperty("ranking_attribute")@field:JsonProperty("ranking_attribute")
        val rankingAttribute: Long? = null,

        @get:JsonProperty("ranking_rarity")@field:JsonProperty("ranking_rarity")
        val rankingRarity: Long? = null,

        @get:JsonProperty("ranking_special")@field:JsonProperty("ranking_special")
        val rankingSpecial: Any? = null
)

data class Idol (
        val note: String? = null,
        val school: String? = null,
        val name: String? = null,
        val year: String? = null,
        val chibi: String? = null,

        @get:JsonProperty("main_unit")@field:JsonProperty("main_unit")
        val mainUnit: String? = null,

        @get:JsonProperty("japanese_name")@field:JsonProperty("japanese_name")
        val japaneseName: String? = null,

        @get:JsonProperty("chibi_small")@field:JsonProperty("chibi_small")
        val chibiSmall: String? = null,

        @get:JsonProperty("sub_unit")@field:JsonProperty("sub_unit")
        val subUnit: String? = null
)
