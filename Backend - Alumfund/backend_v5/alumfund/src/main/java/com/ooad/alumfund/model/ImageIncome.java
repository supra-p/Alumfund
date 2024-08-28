package com.ooad.alumfund.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ImageIncome")
public class ImageIncome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="img")
	@SequenceGenerator(name = "img", sequenceName = "img", initialValue = 1, allocationSize=1)
	private long id;
	private String imageName;
	
	
	private ArrayList<ImageIncome> imageList;

	@Column(name = "picByte", unique = false, nullable = false, length = 100000)
	private byte[] picByte;
	
	
	public ImageIncome() {
		
	}
	public ImageIncome(String imageName, byte[] picByte) {
		this.imageName = imageName;
		this.picByte = picByte;
		imageList = new ArrayList<ImageIncome>();
	}
	
	
	public void addImage(ImageIncome e) {
		   imageList.add(e);
	   }

	   public void removeImage(ImageIncome e) {
		   imageList.remove(e);
	   }

	   public List<ImageIncome> getImageList(){
	     return imageList;
	   }
}
