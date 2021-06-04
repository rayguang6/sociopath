<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="profilePhoto" value="/profilephoto/${userId}" />
<c:url var="editProfileAbout" value="/edit-profile-about" />
<c:url var="saveInterest" value="/save-interest" />
<c:url var="deleteInterest" value="/delete-interest" />


    <!-- INSTRUCTIONS -->

    <!-- 2 CSS files are required: -->
    <!--   * Tag-it's base CSS (jquery.tagit.css). -->
    <!--   * Any theme CSS (either a jQuery UI theme such as "flick", or one that's bundled with Tag-it, e.g. tagit.ui-zendesk.css as in this example.) -->
    <!-- The base CSS and tagit.ui-zendesk.css theme are scoped to the Tag-it widget, so they shouldn't affect anything else in your site, unlike with jQuery UI themes. -->
    <link href="css/jquery.tagit.css" rel="stylesheet" type="text/css">
    <link href="css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">
    <!-- If you want the jQuery UI "flick" theme, you can use this instead, but it's not scoped to just Tag-it like tagit.ui-zendesk is: -->
    <!--   <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css"> -->

    <!-- jQuery and jQuery UI are required dependencies. -->
    <!-- Although we use jQuery 1.4 here, it's tested with the latest too (1.8.3 as of writing this.) -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>

    <!-- The real deal -->
    <script src="js/tag-it.js" type="text/javascript" charset="utf-8"></script>

    <script>
        $(function(){
            var sampleTags = ['c++', 'java', 'php', 'coldfusion', 'javascript', 'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];

            //-------------------------------
            // Minimal
            //-------------------------------
            $('#myTags').tagit();

            //-------------------------------
            // Single field
            //-------------------------------
            $('#singleFieldTags').tagit({
                availableTags: sampleTags,
                // This will make Tag-it submit a single form value, as a comma-delimited field.
                singleField: true,
                singleFieldNode: $('#mySingleField')
            });

            // singleFieldTags2 is an INPUT element, rather than a UL as in the other 
            // examples, so it automatically defaults to singleField.
            $('#singleFieldTags2').tagit({
                availableTags: sampleTags
            });

            //-------------------------------
            // Preloading data in markup
            //-------------------------------
            $('#myULTags').tagit({
                availableTags: sampleTags, // this param is of course optional. it's for autocomplete.
                // configure the name of the input field (will be submitted with form), default: item[tags]
                itemName: 'item',
                fieldName: 'tags'
            });

            //-------------------------------
            // Tag events
            //-------------------------------
            var eventTags = $('#eventTags');

            var addEvent = function(text) {
                $('#events_container').append(text + '<br>');
            };

            eventTags.tagit({
                availableTags: sampleTags,
                beforeTagAdded: function(evt, ui) {
                    if (!ui.duringInitialization) {
                        addEvent('beforeTagAdded: ' + eventTags.tagit('tagLabel', ui.tag));
                    }
                },
                afterTagAdded: function(evt, ui) {
                    if (!ui.duringInitialization) {
                        addEvent('afterTagAdded: ' + eventTags.tagit('tagLabel', ui.tag));
                    }
                },
                beforeTagRemoved: function(evt, ui) {
                    addEvent('beforeTagRemoved: ' + eventTags.tagit('tagLabel', ui.tag));
                },
                afterTagRemoved: function(evt, ui) {
                    addEvent('afterTagRemoved: ' + eventTags.tagit('tagLabel', ui.tag));
                },
                onTagClicked: function(evt, ui) {
                    addEvent('onTagClicked: ' + eventTags.tagit('tagLabel', ui.tag));
                },
                onTagExists: function(evt, ui) {
                    addEvent('onTagExists: ' + eventTags.tagit('tagLabel', ui.existingTag));
                }
            });

            //-------------------------------
            // Read-only
            //-------------------------------
            $('#readOnlyTags').tagit({
                readOnly: true
            });

            //-------------------------------
            // Tag-it methods
            //-------------------------------
            $('#methodTags').tagit({
                availableTags: sampleTags
            });

            //-------------------------------
            // Allow spaces without quotes.
            //-------------------------------
            $('#allowSpacesTags').tagit({
                availableTags: sampleTags,
                allowSpaces: true
            });

            //-------------------------------
            // Remove confirmation
            //-------------------------------
            $('#removeConfirmationTags').tagit({
                availableTags: sampleTags,
                removeConfirmation: true
            });
            
        });
    </script>
    






















<!-- Above is testing tagIt JS -->




<div class="row">

	<div class="col-md-10 col-md-offset-1">
	
	<div id="profile-photo-status"></div>


		<div id="interestDiv">
			<ul id="interestList">
				<c:choose>
					<c:when test="${empty profile.interests}">
						<li>Add your interests here (example: music)!</li>
					</c:when>
					<c:otherwise>
						<c:forEach var="interest" items="${profile.interests}">
							<li>${interest}</li>
						</c:forEach>
					</c:otherwise>

				</c:choose>
			</ul>
		</div>


		<div class="profile-about">

			<div class="profile-image">
				<div>
					<img id="profilePhotoImage" src="${profilePhoto}" />
				</div>
				<div class="text-center">
					<c:if test="${ownProfile == true}">
						<a href="#" id="uploadLink">Upload photo</a>
					</c:if>
				</div>
			</div>


			<div class="profile-text">
				<c:choose>
					<c:when test="${profile.about == null}">
				Click 'edit' to add information about yourself to your profile
				</c:when>
					<c:otherwise>
						${profile.about}
					</c:otherwise>
				</c:choose>

			</div>
		</div>

		<div class="profile-about-edit">
			<c:if test="${ownProfile == true}">
				<a href="${editProfileAbout}">edit</a>
			</c:if>
		</div>

		<c:url value="/upload-profile-photo" var="uploadPhotoLink" />
		<form method="post" enctype="multipart/form-data" id="photoUploadForm"
			action="${uploadPhotoLink}">

			<input type="file" accept="image/*" name="file" id="photoFileInput"/> <input
				type="submit" value="upload" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>

	</div>



</div>


<script>
function setUploadStatusText(text) {
	$("#profile-photo-status").text(text);
	
	window.setTimeout(function() {
		$("#profile-photo-status").text("");
	}, 2000);
}
function uploadSuccess(data) {
	
	$("#profilePhotoImage").attr("src", "${profilePhoto}?t=" + new Date());
	
	$("#photoFileInput").val("");
	
	setUploadStatusText(data.message);
}

	function uploadPhoto(event) {
		
		$.ajax({
			url: $(this).attr("action"),
			type: 'POST',
			data: new FormData(this),
			processData: false,
			contentType: false,
			success: uploadSuccess,
			error: function() {
				setUploadStatusText("Server unreachable");
			}
		});
		
		event.preventDefault();
	}


	function saveInterest(text) {
		editInterest(text, "${saveInterest}");
	}
	function deleteInterest(text) {
		editInterest(text, "${deleteInterest}");
	}
	function editInterest(text, actionUrl) {
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
			jqXHR.setRequestHeader(header, token);
		});

		$.ajax({
			'url' : actionUrl,
			data : {
				'name' : text
			},
			type : 'POST',
			success : function() {
				//alert("Ok");
			},
			error : function() {
				//alert("error");
			}
		});

		
	}




$(document).ready(function() {



	$("#interestList").tagit({
		afterTagRemoved : function(event, ui) {
			deleteInterest(ui.tagLabel);
		},
		afterTagAdded : function(event, ui) {
			if (ui.duringInitialization != true) {
				saveInterest(ui.tagLabel);
			}
		},
		caseSensitive : false,
		allowSpaces : true,
		tagLimit : 10,
		availableTags:['basketball','music','coding'],
		readOnly: '${ownProfile}' == 'false'
	});
	
	
	$("#uploadLink").click(function(event) {
		event.preventDefault();
		$("#photoFileInput").trigger('click');
	});
	
	$("#photoFileInput").change(function() {
		$("#photoUploadForm").submit();
	});
	
	$("#photoUploadForm").on("submit", uploadPhoto);
});
</script>