package sk.lubostar.crz.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contract(val id: Long,
                    val contract_identifier: String,
                    val contracting_authority_name: String,
                    val supplier_name: String,
                    val subject: String,
                    val signed_on: String,
                    val contract_price_amount: Double,
                    val contract_price_total_amount: Double) {
    fun getPriceAmount() = "$contract_price_amount EUR"
}