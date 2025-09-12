package com.movie.dto;

public class MovieDTO {

	// Mesmo nome que está no arquivo json que vamos puxar do git
	private String title;

	public MovieDTO() {

	}

	public MovieDTO(String title) {

		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
