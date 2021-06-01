<#macro login path isRegisterForm>
  <form action="${path}" method="post">
    <div>
      <label>User Name :
        <input type="text" name="username" placeholder="User name"/>
        <#if usernameError??>
          <div class="invalid-feedback">
            ${usernameError}
          </div>
        </#if>
      </label>
    </div>

    <div>
      <label>Password :
        <input type="password" name="password" placeholder="password"/>
        <#if passwordError??>
          <div class="invalid-feedback">
            ${passwordError}
          </div>
        </#if>
      </label>
    </div>
    <#if isRegisterForm>
      <div>
        <label>Email :
          <input type="email" name="email" placeholder="email"/>
          <#if emailError??>
            <div class="invalid-feedback">
              ${emailError}
            </div>
          </#if>
        </label>
      </div>
    </#if>
    <button type="submit">
      <#if isRegisterForm>Create <#else>Sign in</#if></button>
  </form>
</#macro>
<#macro logout>
  <form action="/logout" method="post">
    <button type="submit">Sign out</button>
  </form>
</#macro>