package com.project.pojos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@ToString
//@NoArgsConstructor
@AllArgsConstructor
@Table(name="Project_Info")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long proj_Id;
	
	@Column(name="Title",length=50)
	@NotBlank(message = "Project Name is required!!!!")
	private String title;
	
	@Column(name="decription",length=300)
	@NotBlank(message = "Description is required!!!!")
	private String description;
	
	@Column(name="srs_path")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String srsPath;
	
	@Column(name="ppt_path")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String pptPath;
	
	@Column(name="report_path")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String reportPath;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Status")
	private Status status;
	
	@Column(name="feedback",length = 1000)
	private String feedback;
	
	@Column(name="ratings")
	private int ratings;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne
	@JoinColumn(name = "prn_no")
	private Student std;
	
	
	@ManyToOne
	private Faculty faculty;
	
	public Project(@NotBlank(message = "Project Name is required!!!!") String title,
			@NotBlank(message = "Description is required!!!!") String description) {
		super();
		this.title = title;
		this.description = description;
		this.status=status.NEW;		
	}
	
	

	public void updateProject(Project proj) {
		this.title = proj.title;
		this.description = proj.description;
		
	}
	

	public Long getProj_Id() {
		return proj_Id;
	}

	public void setProj_Id(Long proj_Id) {
		this.proj_Id = proj_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSrsPath() {
		return srsPath;
	}

	public void setSrsPath(String srsPath) {
		this.srsPath = srsPath;
	}

	public String getPptPath() {
		return pptPath;
	}

	public void setPptPath(String pptPath) {
		this.pptPath = pptPath;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}


	public Student getStd() {
		return std;
	}


	public void setStd(Student std) {
		this.std = std;
	}


//	public Faculty getFaculty() {
//		return faculty;
//	}


//	public void setFaculty(Faculty faculty) {
//		this.faculty = faculty;
//	}

	public void setStudentProjectDetails(Student studentProjectDetails) {
		this.std = studentProjectDetails;
		
	}
	
	public Student getStudentProjectDetails() {
		return std;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}



	public Project() {
		super();
		this.status=status.NEW;	
		
	}

	
}
