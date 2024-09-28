package com.together_english.deiz.controller

import com.together_english.deiz.model.common.MainResponse
import com.together_english.deiz.model.member.entity.Member
import com.together_english.deiz.security.service.MemberService
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/member")
@Tag(name = "유저 API", description = "유저 정보 조회 및 업데이트를 위한 API")
class MemberController(
        private val memberService: MemberService
) {

    @Operation(
            summary = "프로파일 수정",
            description = "유저의 프로파일을 수정합니다. data 값에 업데이트된 프로필 url이 return됩니다.",
            security = [SecurityRequirement(name = "Authorization")]
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Operation completed successfully."),
        ApiResponse(responseCode = "400", description = "Bad Request: Invalid input data.")
    ])
    @PostMapping("/profile", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE] )
    fun changeProfile(
            @RequestParam("file") file: MultipartFile,
            @Parameter(hidden = true) member: Member
    ) : ResponseEntity<MainResponse<String>> {
        val updatedProfile = memberService.updateMemberProfile(file, member.email)
        return ResponseEntity.ok(MainResponse.getSuccessResponse(updatedProfile))
    }
}