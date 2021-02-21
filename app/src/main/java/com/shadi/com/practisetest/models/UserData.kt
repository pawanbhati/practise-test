package com.shadi.com.practisetest.models

import androidx.room.*
import com.shadi.com.practisetest.database.Converters

/**
 * This is a data class which has two roles.
 * 1. POJO class use by the retrofit to retrieve the data from server
 * 2. Database tables
 *
 * @author Pawan Bhati
 * @since 20-2-2021
 **/
data class UserData(
	val results: List<ResultsItem?>? = null,
	val info: Info? = null
)

data class Dob(
	val date: String? = null,
	val age: Int? = null
)

data class Id(
	val name: String? = null,
	val value: Any? = null
)

data class Name(
	val last: String? = null,
	val title: String? = null,
	val first: String? = null
)

data class Timezone(
	val offset: String? = null,
	val description: String? = null
)

data class Location(
	val country: String? = null,
	val city: String? = null,
	val street: Street? = null,
	val timezone: Timezone? = null,
	val coordinates: Coordinates? = null,
	val state: String? = null
)

data class Picture(
	val thumbnail: String? = null,
	val large: String? = null,
	val medium: String? = null
)

data class Street(
	val number: Int? = null,
	val name: String? = null
)

data class Registered(
	val date: String? = null,
	val age: Int? = null
)

data class Coordinates(
	val latitude: String? = null,
	val longitude: String? = null
)

data class Info(
	val seed: String? = null,
	val page: Int? = null,
	val results: Int? = null,
	val version: String? = null
)

@Entity(tableName = "user_table")
data class ResultsItem(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "db_id") var db_id: Int,
	@ColumnInfo(name = "nat") val nat: String? = null,
	@ColumnInfo(name = "gender") val gender: String? = null,
	@ColumnInfo(name = "phone") val phone: String? = null,
	@TypeConverters(Converters::class)
	val dob: Dob? = null,
	@ColumnInfo(name = "isInvitationAccepted")
	var isInvitationAccepted: Boolean? = null,
	@TypeConverters(Converters::class)
	val name: Name? = null,
	@TypeConverters(Converters::class)
	val registered: Registered? = null,
	@TypeConverters(Converters::class)
	val location: Location? = null,
	@TypeConverters(Converters::class)
	val id: Id? = null,
	@Embedded
	val login: Login? = null,
	@Embedded
	val cell: String? = null,
	@Embedded
	val email: String? = null,
	@Embedded
	val picture: Picture? = null
)

data class Login(
	val sha1: String? = null,
	val password: String? = null,
	val salt: String? = null,
	val sha256: String? = null,
	val uuid: String? = null,
	val username: String? = null,
	val md5: String? = null
)

