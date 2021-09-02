package com.postcode.au.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.postcode.au.api.entity.PostCodeRecord;
import com.postcode.au.api.entity.SearchResult;
import com.postcode.au.api.exception.AuthenticationException;
import com.postcode.au.api.exception.ResourceNotFoundException;
import com.postcode.au.api.model.AuthenticationRequest;
import com.postcode.au.api.model.AuthenticationResponse;
import com.postcode.au.api.service.PostCodeServices;
import com.postcode.au.api.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Api(description = "Postcode API")
public class PostCodeController {

    @Autowired
    private PostCodeServices service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService detailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/welcome")
    public String home() {
        return "Welcome to Post Code API";
    }

    /**
     * Restful API to get token
     * 
     * @param AuthenticationRequest
     * @return JWT token
     */
    @RequestMapping(value = "/api/v1/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Authentication Failed");
        }

        final UserDetails userDetails = detailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * Restful API to search by postcode
     * 
     * @param postcode must be 4 digits (Australian postcode)
     * @return The JSON Response containing postcode ,suburb and state
     */
    @RequestMapping(value = "/api/v1/postcode/{postcode}", method = RequestMethod.GET)
    public SearchResult search(
            @Valid @Pattern(regexp = "([0-9]{4})", message = "Invalid Australian PostCode Format") @PathVariable("postcode") String postcode) {

        SearchResult result = new SearchResult();
        result.setStatus(PostCodeServices.API_SUCCESS_STATUS);
        List<PostCodeRecord> searchResult = service.searchByPostCode(postcode);
        if (searchResult.isEmpty()) {
            throw new ResourceNotFoundException("No details Found!");
        }

        result.setResult(searchResult);
        return result;
    }

    /**
     * Restful API to search by suburb
     * 
     * @param suburb The Australian suburb Name
     * @return The JSON Response containing postcode ,suburb and state
     */
    @RequestMapping(value = "/api/v1/suburb/{suburbName}", method = RequestMethod.GET)
    public SearchResult searchByStateAndsuburb(@PathVariable("suburbName") String suburbName) {
        SearchResult result = new SearchResult();
        result.setStatus(PostCodeServices.API_SUCCESS_STATUS);
        List<PostCodeRecord> searchResult = service.searchBysuburb(suburbName);
        if (searchResult.isEmpty()) {
            throw new ResourceNotFoundException("No details Found!");
        }

        result.setResult(searchResult);
        return result;
    }

    /**
     * The Restful API to add new Postcode record to the database
     * 
     * @param newRecord
     * @return
     */
    @RequestMapping(value = "/api/v1/secure/addrecord", method = RequestMethod.POST)
    public PostCodeRecord addNewRecord(@Valid @RequestBody PostCodeRecord newRecord) {
        return service.saveNewRecord(newRecord);
    }

}
