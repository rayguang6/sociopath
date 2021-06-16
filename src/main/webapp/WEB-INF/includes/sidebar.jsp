
<div id="sideBarContainer">
	<nav class="sideBar">

		<div class="group">

			<div class="navItem">
				<span role='link' tabindex='0' onclick='openPage("/profile")'
					class="navItemLink">
					<a href="${contextRoot}/profile">
									<img id="sidePhoto" class="profilePhoto" src="img/avatar${loggedIn}.png" onerror="this.onerror=null; this.src='img/avatar.png'" alt="Profile Image" >	
				
					<c:out value="${loggedIn}"></c:out>
					</a>
				</span>
				
			</div>

		</div>

		<div class="group">
		
			
			<span class="sideDiv">
				<a href="${contextRoot}/FindStranger" class="sideATag">
				<span class="navItem">
				<img src="img/teach.png" class="sideIcon"> <span role="link" tabindex="0" 
						class="navItemLink">Ask Stranger</span>
						
						</span></a>
			</span>
			
			<span class="sideDiv">
				<a href="${contextRoot}/MyFriends" class="sideATag">
				<span class="navItem">
				<img src="img/chitchat.png" class="sideIcon"> <span role="link" tabindex="0" 
						class="navItemLink">Chit chat</span>
						
						</span></a>
			</span>
			
			<span class="sideDiv">
				<a href="${contextRoot}/ScheduleLunch" class="sideATag">
				<span class="navItem">
				<img src="img/lunch.png" class="sideIcon"> <span role="link" tabindex="0" 
						class="navItemLink">Schedule My Lunch</span>
						
						</span></a>
			</span>
			
			<span class="sideDiv">
				<a href="${contextRoot}/ArrangeBook" class="sideATag">
				<span class="navItem">
				<img src="img/library.png" class="sideIcon"> <span role="link" tabindex="0" 
						class="navItemLink">Arrange Book</span>
						
						</span></a>
			</span>
			
			<span class="sideDiv">
				<a href="${contextRoot}/MeetCrush" class="sideATag">
				<span class="navItem">
				<img src="img/crush.png" class="sideIcon"> <span role="link" tabindex="0" 
						class="navItemLink">Meet Crush </span>
						
						</span></a>
			</span>
			
			<span class="sideDiv">
				<a href="${contextRoot}/Friendship" class="sideATag">
				<span class="navItem">
				<img src="img/friendship.png" class="sideIcon"> <span role="link" tabindex="0" 
						class="navItemLink">Friendship</span>
						
						</span></a>
			</span>
			
			
		
		</div>

	</nav>
</div>
