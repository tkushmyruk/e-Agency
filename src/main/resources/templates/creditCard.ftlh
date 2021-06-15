<#import "parts/common.ftlh" as c/>
<@c.main>
  <#if !creditCard??>
    <h2>Add your card</h2>
    <div>
      <form method="post">
        <div class="form-group row">
          <label class="col-sm-1 col-form-label-1">Card Number: </label>
          <div class="col-sm-3">
            <input type="text" name="cardNumber"
                   class="form-control ${(cardNumberError??)?string('is-invalid', '')}" placeholder="card number"/>
            <#if cardNumberError??>
              <div class="invalid-feedback">
                ${cardNumberError}
              </div>
            </#if>
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-1 col-form-label-1">Password: </label>
          <div class="col-sm-3">
            <input type="text" name="cardPassword"
                   class="form-control ${(cardPasswordError??)?string('is-invalid', '')}" placeholder="card password"/>
            <#if cardPasswordError??>
              <div class="invalid-feedback">
                ${cardPasswordError}
              </div>
            </#if>
          </div>
        </div>
        <button class="btn btn-primary" type="submit">Save Card</button>
      </form>
    </div>
  <#else >
  <h2>Your card - ${creditCard.getCardNumber()}</h2>
  <div>Your balance on card - <p>${creditCard.getBalance()}</p></div>
  <a href="/creditCard/topUp">Top up your card</a>
  </#if>
</@c.main>