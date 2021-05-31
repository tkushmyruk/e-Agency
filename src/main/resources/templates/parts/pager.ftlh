<#macro pager url page>
  <div>
    <#list 1..page.getTotalPages() as p>
      <a href="${url}?page=${p-1}&amp;size=4" tabindex="-1">${p}</a>
    </#list>
  </div>
</#macro>