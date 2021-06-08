<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="img" value="/img" />
<c:url var="editProfileAbout" value="/edit-profile-about" />

<div class="row">

	<div class="col-md-10 col-md-offset-1">
	
		<h1>The Profile Of  ${student.username}</h1>

		<div class="profile-about">

			<div class="profile-image">
				<img src="${img}/avatar.png" width="70"/>
			</div>


			<div class="profile-text">
				<c:choose>
					<c:when test="${student.about == null}">
				Click 'edit' to add information about yourself to your profile
				</c:when>
					<c:otherwise>
						${student.about}
					</c:otherwise>
				</c:choose>
				
				
				
				
				<!-- Listing Student Details -->
				<br>${student.id} id 
				<br>${student.user} user
				<br>${student.username} username
				<br>${student.about} about
				<br>${student.photoDirectory} photoDirectory
				<br>${student.photoName} photoName
				<br>${student.photoExtension}  photoExtension
				<br>${student.vertexInfo}  vertexInfo
				<br>${student.reputation}  reputation
				<br>${student.divingrate}  divingrate
				<br>${student.average_lunchStart}  average_lunchStart
				<br>${student.average_lunchPeriod}  average_lunchPeriod
				<br>${student.end_time} end_time
				<br>${student.lunchStart} lunchStart
				<br>${student.lunchPeriod} lunchPeriod
				<br>${student.outdeg} outdeg
				<br>${student.indeg}indeg

			</div>
		</div>

		<div class="profile-about-edit">
			<a href="${editProfileAbout}">edit</a>
		</div>


		<p>&nbsp;</p>
		
		<c:url value="/upload-profile-photo" var="uploadPhotoLink" />
		<form method="post" enctype="multipart/form-data" action="${uploadPhotoLink}">
			
			select photo: <input type="file" accept="image/*" name="file" />
			<input type="submit" value="upload" />
			
			<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
		
		</form>

	</div>



</div>