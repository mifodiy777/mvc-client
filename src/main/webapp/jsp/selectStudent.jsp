<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<li>
    <select name="student" class="form-control">
        <c:forEach items="${studentList}" var="student">
            <option value="${student.id}"
                    <c:if test="${selectStudent.id eq student.id}">selected</c:if>>${student.surname} ${student.name}</option>
        </c:forEach>
        <c:if test="${empty studentList}">
            <option value="">Нет студентов для добавления</option>
        </c:if>
    </select>
</li>

