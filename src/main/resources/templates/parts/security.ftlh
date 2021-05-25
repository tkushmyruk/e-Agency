<#assign
know = Session.SPRING_SECURITY_CONTEXT??
/>
<#if know>
  <#assign user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
  name = user.getUsername()
  isAdmin = user.isAdmin()
  isManager = false/>
  <#elseif know>
    <#assign user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
      isAdmin = false
    isManager = user.isManager()/>
<#else >
  <#assign
  name = "User"
  isAdmin = false
    isManager = false
  />
</#if>