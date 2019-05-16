package com.tjf.checkin.survey.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;
import com.totvs.tjf.core.validation.ValidatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@CrossOrigin
@RestController("RequestControllerV2")
@RequestMapping(path = "/api/v2/surveys", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class SurveyController {

}
