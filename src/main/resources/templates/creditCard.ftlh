<#import "parts/common.ftlh" as c/>
<@c.main>
  <#if !creditCard??>
    <h2>Add your card</h2>
    <div>
      <form method="post">
        <div>
          <label>Card Number : <input type="text" name="cardNumber" placeholder="Card Number"/></label>
        </div>
        <div>
          <label>Card Password : <input type="password" name="cardPassword" placeholder="Card Password"/></label>
        </div>
        <button type="submit">Save Card</button>
      </form>
    </div>
  <#else >
  <h2>Your card - ${creditCard.getCardNumber()}</h2>
  <div>Your balance on card - <p>${creditCard.getBalance()}</p></div>
  <a href="/creditCard/topUp">Top up your card</a>
  </#if>
</@c.main>