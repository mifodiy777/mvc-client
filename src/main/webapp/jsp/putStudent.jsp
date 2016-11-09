<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    $(document).ready(function () {
        $("#putStudentForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        showSuccessMessage(html);
                        closeForm();
                        return false;
                    },
                    error: function (xhr) {
                        if (xhr.status == 409) {
                            showErrorMessage(xhr.responseText);
                        }
                    }
                });
            },
            errorPlacement: function (error, element) {
                validPlaceError(error, element)
            },
            success: function (label, element) {
                validPlaceSuccess(label, element);
            }
        });


        $("#putStudentForm").submit(function (e) {
            e.preventDefault();
            $(this).valid();
            return false;
        });
    });
</script>
<div class="panel panel-success">
    <div class="panel-heading">
        <h4 class="panel-title">${type}</h4>
    </div>

    <form action="putSaveStudent" id="putStudentForm" method="post">
        <div class="panel-body">
            <h4>Занятие: ${lesson.topic}</h4>
            <input type="hidden" name="id" value="${lesson.id}">
            <h4>Студент:</h4>
            <div class="form-group">
                <select name="student" class="form-control">
                    <c:forEach items="${studentList}" var="student">
                        <option value="${student.id}">${student.surname} ${student.name}</option>
                    </c:forEach>
                    <c:if test="${empty studentList}">
                        <option value="">Нет студентов для добавления</option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="panel-footer">
            <c:if test="${not empty studentList}">
                <button class="btn btn-success">
                    <span class="glyphicon glyphicon-ok"></span> Сохранить
                </button>
            </c:if>

            <button type="reset" onclick="closeForm();"
                    class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Закрыть
            </button>
        </div>
    </form>
</div>
