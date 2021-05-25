<#import "parts/common.ftlh" as c/>
<@c.main>
  <h2>Top Up Card</h2>
  <#if message??><h3>${message}</h3></#if>
  <div>
    <form method="post">
      <div>
        <label>USD : <input type="number" step="0.01" name="money" placeholder="Replenishment"></label>
      </div>
      <div>
        <label>Card Password : <input type="password" name="cardPassword" placeholder="Card Password"></label>
      </div>
      <button type="submit">Replenish</button>
    </form>
  </div>
</@c.main>