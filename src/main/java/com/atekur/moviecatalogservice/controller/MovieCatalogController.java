package com.atekur.moviecatalogservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atekur.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		return Collections.singletonList(new CatalogItem("Transformer", "Si-Fi movie", 4));
	}
}
