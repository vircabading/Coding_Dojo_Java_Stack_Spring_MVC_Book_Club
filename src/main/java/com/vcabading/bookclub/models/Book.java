package com.vcabading.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//////////////////////////////////////////////////////////////
//	BOOK CLASS
//////////////////////////////////////////////////////////////

@Entity
@Table(name="books")
public class Book {

	//	//// FIELDS //////////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min=1, max=100, message="Title must be one to one hundred characters in length")
	private String title;
	
	@NotNull
	@Size(min=1, max=55, message="Title must be one to fifty-five characters in length")
	private String author;
	
	@NotNull
	@Size(min=1, max=255, message="Thoughts must be one to two hundred fifty-five characters in length")
	private String myThoughts;
	
	@Column(updatable=false)		// this will not allow createdAt to be updated after creation
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate()	{
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	//	//// CONSTRUCTORS ////////////////////////////////////

	public Book() {}

	//	//// GETTERS AND SETTERS /////////////////////////////
	
}