<#macro login path isRegisterForm>
  <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
    <div>
      <label>User Name :
        <input type="text" name="username" placeholder="User name"/>
        <#if usernameError??>
          <div>
            ${usernameError}
          </div>
        </#if>
      </label>
    </div>

    <div>
      <label>Password :
        <input type="password" name="password" placeholder="password"/>
        <#if passwordError??>
          <div>
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
            <div>
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
    <input type="submit" value="Sign out"/>
  </form>
</#macro>