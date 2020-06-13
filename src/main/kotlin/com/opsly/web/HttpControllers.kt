package com.opsly.web

import org.springframework.web.bind.annotation.*

@RestController
class HttpControllers {

	@GetMapping("/")
	@ResponseBody
//	fun root (): String = Jason.main()
//	fun root (): String = Main.run()
	fun root (): String = MainWoker.run()
}

//
// *******  Below code is for testing non related Kotlin features and can be ignored.
//
//
//@RestController
//@RequestMapping("/api/article")
//class ArticleController(private val repository: ArticleRepository) {
//
//	@GetMapping("/")
//	fun findAll() = repository.findAllByOrderByAddedAtDesc()
//
//	@GetMapping("/{slug}")
//	fun findOne(@PathVariable slug: String) =
//			repository.findBySlug(slug) ?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")
//
//}
//
//@RestController
//@RequestMapping("/api/user")
//class UserController(private val repository: UserRepository) {
//
//	@GetMapping("/")
//	fun findAll() = repository.findAll()
//
//	@GetMapping("/{login}")
//	fun findOne(@PathVariable login: String) = repository.findByLogin(login) ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
//}
