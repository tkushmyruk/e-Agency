<#macro login path isRegisterForm>
  <form action="${path}" method="post">
    <div>
      <label>User Name : <input type="text" name="username" placeholder="User name"/></label>
    </div>

    <div>
      <label>Password :
        <input type="password" name="password" placeholder="password"/>
      </label>
    </div>
    <#if isRegisterForm>
      <div>
        <label>Email : <input type="email" name="email" placeholder="email"/></label>
      </div>
    </#if>
    <#if !isRegisterForm><div><a href="/registration">Add new user</a></div></#if>
    <button type="submit">
      <#if isRegisterForm>Create <#else>Sign in</#if></button>
  </form>
</#macro>
<#macro logout>
  <form action="/logout" method="post">
    <button type="submit">Sign out</button>
  </form>
</#macro>