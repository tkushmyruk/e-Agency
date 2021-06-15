<#macro login path isRegisterForm>
  <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
    <div class="form-group row">
      <label class="col-sm-1 col-form-label-1">Username: </label>
      <div class="col-sm-3">
        <input type="text" name="username" value="<#if user??>${user.username}</#if>"
               class="form-control ${(usernameError??)?string('is-invalid', '')}" placeholder="username"/>
        <#if usernameError??>
          <div class="invalid-feedback">
            ${usernameError}
          </div>
        </#if>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-1 col-form-label-1">Password:  </label>
      <div class="col-sm-3">
        <input type="password" name="password"
               class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="password">
        <#if passwordError??>
          <div class="invalid-feedback">
            ${passwordError}
          </div>
        </#if>
      </div>
    </div>
    <#if isRegisterForm>
      <div class="form-group row">
        <label class="col-sm-1 col-form-label-1">Email :</label>
        <div class="col-sm-3">
          <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}" placeholder="email"/>
          <#if emailError??>
            <div class="invalid-feedback">
              ${emailError}
            </div>
          </#if>
        </div>
      </div>
    </#if>
    <button class="btn btn-primary" type="submit">
      <#if isRegisterForm>Create <#else>Sign in</#if></button>
  </form>
</#macro>
<#macro logout>
  <form action="/logout" method="post">
    <button class="btn btn-primary" type="submit">Sign out</button>
  </form>
</#macro>