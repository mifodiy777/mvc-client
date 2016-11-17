<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/>
<security:authentication property="principal" var="user"/>
<script type="text/javascript">

    $(document).ready(function () {
        $("#studentForm").validate({
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function (html) {
                        $('#studentTable').DataTable().ajax.reload(null, false);
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

        $(".datepicker").datepicker({
            autoclose: true,
            format: "dd.mm.yyyy",
            language: 'ru',
            todayBtn: true
        }).on('changeDate', function (ev) {
            $(this).valid();
        });

        $("#studentForm").submit(function (e) {
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

    <form:form modelAttribute="student" id="studentForm" method="post" action="saveStudent">
        <form:hidden path="id"/>
        <form:hidden path="version"/>
        <div class="panel-body">
            <div class="divider"><h4> Студент: </h4></div>

            <div class="col-md-6">
                <div class="form-group input-group">
                    <label for="surname" class="control-label">Фамилия</label>
                    <form:input path="surname" id="surname" cssClass="required form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group input-group">
                    <label for="name" class="control-label">Имя*</label>
                    <form:input path="name" id="name" cssClass="form-control"/>
                    <span class="help-block"></span>
                </div>
                <div class="form-group input-group">
                    <label for="gender" class="control-label">Пол</label>
                    <form:select path="gender" cssClass="form-control">
                        <form:option value="Муж"/>
                        <form:option value="Жен"/>
                    </form:select>
                    <span class="help-block"></span>
                </div>
                <div class="form-group input-group">
                    <label for="birthday" class="control-label">Дата рождения</label>
                    <form:input path="birthday" id="birthday" cssClass="dateRU datepicker form-control"/>
                    <span class="help-block"></span>
                </div>
                <h4>Кол - во посещенных лекций: <c:out value="${sumLesson}"/></h4>
            </div>
        </div>
        <div class="panel-footer">
            <c:if test="${isAdmin}">
                <button class="btn btn-success">
                    <span class="glyphicon glyphicon-ok"></span> Сохранить
                </button>
            </c:if>

            <button type="reset" onclick="closeForm('garag'); $('#addGaragButton').show();"
                    class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Закрыть
            </button>
        </div>
    </form:form>
</div>
