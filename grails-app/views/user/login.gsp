<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
<body>
<a href="#login-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul><li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li></ul>
</div>
<div id="login-user" class="content scaffold-create" role="main">
    <h1><g:message code="default.login.label" default='Login' args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${userInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="authenticate" >
        <fieldset class="form">
            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} required">
                <label for="login">
                    <g:message code="user.login.label" default="Login" />
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="login" value="${userInstance?.login}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
                <label for="password">
                    <g:message code="user.password.label" default="Password" />
                    <span class="required-indicator">*</span>
                </label>
                <g:field type="password" name="password" value="${userInstance?.password}"/>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="login" class="save" value="${message(code: 'default.button.login.label', default: 'Login')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>