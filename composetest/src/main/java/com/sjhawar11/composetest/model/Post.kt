package com.sjhawar11.composetest.model

data class Post(
  val id: String,
  val username: String,
  val content: String,
  val likeCount: Int,
  val isLiked: Boolean
)