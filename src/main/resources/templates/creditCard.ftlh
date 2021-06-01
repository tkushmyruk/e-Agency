<#import "parts/common.ftlh" as c/>
<@c.main>
  <#if !creditCard??>
    <h2>Add your card</h2>
    <div>
      <form method="post">
        <div>
          <label>Card Number :
            <input type="text" name="cardNumber" placeholder="Card Number"/>
          </label>
          <#if cardNumberError??>
            <div class="invalid-feedback">
              ${cardNubmerError}
            </div>
          </#if>
        </div>
        <div>
          <label>Card Password :
            <input type="password" name="cardPassword" placeholder="Card Password"/>
          </label>
          <#if cardPasswordError??>
            <div class="invalid-feedback">
              ${cardPasswordError}
            </div>
          </#if>
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