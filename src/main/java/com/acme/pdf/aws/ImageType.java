package com.acme.pdf.aws;

public enum ImageType {
	
    JPEG("image/jpeg"), PNG("image/png");

	private String mimetype;
	
	private ImageType(String mimetype) {
		this.mimetype = mimetype;
	}
	
	public static ImageType valueOfMimetype(String mimetype) {
		for (ImageType t : values()) {
			if (t.mimetype.equals(mimetype)) {
				return t;
			}
		}
		
		return null;
	}
	
}
