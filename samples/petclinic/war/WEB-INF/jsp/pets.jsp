<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Veterinarians:</h2>

<table>
  <tr>
  <thead>
    <th>Name</th>
  </thead>
  </tr>
  <c:forEach var="pet" items="${pets}">
    <tr>
      <td>${pet.name}</td>
    </tr>
  </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
