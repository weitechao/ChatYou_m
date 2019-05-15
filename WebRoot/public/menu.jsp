<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<nav class="main-menu">
	<ul>
		${menu}
	</ul>
	<ul class="logout">
		<li>
		    <a href="${pageContext.request.contextPath}/admin/logout.htm"> <i class="icon-off nav-icon"></i> <span
				class="nav-text">退出登陆</span>
			</a>
		</li>
	</ul>
</nav>
