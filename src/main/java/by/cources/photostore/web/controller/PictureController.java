package by.cources.photostore.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.cources.photostore.dal.PictureDao;
import by.cources.photostore.exception.DalException;
import by.cources.photostore.model.Picture;
import by.cources.photostore.model.User;
import by.cources.photostore.service.PictureService;

@Controller
@RequestMapping("/pictures")
public class PictureController {

	private static final Logger LOGGER = Logger
			.getLogger(PictureController.class);

	@Autowired
	private PictureService pictureService;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		try {
			List<Picture> pictures = pictureService.list(Picture.class);
			model.addAttribute("pictures", pictures);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		LOGGER.info("list()");
		return "pictures/list";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		try {
			pictureService.delete(Picture.class, id);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		return "forward:/pictures";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model Model) {
		Picture picture = new Picture();
		try {
			picture = pictureService.find(Picture.class, id);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		Model.addAttribute("picture", picture);
		return "pictures/show";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(Picture picture, Model model,
			@RequestParam(value = "file", required = false) Part file) {
		LOGGER.info("Updating pictures");

		// Process upload file
		if (file != null) {
			LOGGER.info("File name: " + file.getName());
			LOGGER.info("File size: " + file.getSize());
			LOGGER.info("File content type: " + file.getContentType());
			byte[] fileContent = null;
			try {
				InputStream inputStream = file.getInputStream();
				if (inputStream == null)
					LOGGER.info("File inputstream is null");
				fileContent = IOUtils.toByteArray(inputStream);
				picture.setPhoto(fileContent);
			} catch (IOException ex) {
				LOGGER.error("Error saving uploaded file");
			}
			picture.setPhoto(fileContent);
		}

		try {
			pictureService.merge(picture);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}

		return "redirect:/pictures/" + picture.getId().toString();
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {

		Picture picture = null;
		try {
			picture = pictureService.find(Picture.class, id);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}

		if (picture.getPhoto() != null) {
			LOGGER.info("Downloading photo for id:" + picture.getId()
					+ " with size: {} " + picture.getPhoto().length);
		}

		return picture.getPhoto();
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("picture",
					pictureService.find(Picture.class, id));
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		return "pictures/update";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(Picture picture,
			@RequestParam(value = "file", required = false) Part file) {

		LOGGER.info("Creating picture");

		// Process upload file
		if (file != null) {
			LOGGER.info("File name: " + file.getName());
			LOGGER.info("File size: " + file.getSize());
			LOGGER.info("File content type: " + file.getContentType());
			byte[] fileContent = null;
			try {
				InputStream inputStream = file.getInputStream();
				if (inputStream == null)
					LOGGER.info("File inputstream is null");
				fileContent = IOUtils.toByteArray(inputStream);
				picture.setPhoto(fileContent);
			} catch (IOException ex) {
				LOGGER.error("Error saving uploaded file");
			}
			picture.setPhoto(fileContent);
		}

		try {
			picture = pictureService.merge(picture);
		} catch (DalException e) {
			LOGGER.info(e.getMessage());
		}
		LOGGER.info("before getId()");
		LOGGER.info("redirect and getId()" + "redirect:/pictures/"
				+ picture.getId().toString());
		return "redirect:/pictures/" + picture.getId().toString();
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model model) {
		Picture picture = new Picture();
		model.addAttribute("picture", picture);
		return "pictures/create";
	}
}
