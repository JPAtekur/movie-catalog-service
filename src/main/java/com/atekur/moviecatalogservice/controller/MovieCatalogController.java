package com.atekur.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atekur.moviecatalogservice.models.CatalogItem;
import com.atekur.moviecatalogservice.models.Movie;
import com.atekur.moviecatalogservice.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRatings userRatings = restTemplate.getForObject("http://localhost:8083/rating/user/" + userId, UserRatings.class);

		return userRatings.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Si-Fi movie", rating.getRating());
		}).collect(Collectors.toList());
	}
}
