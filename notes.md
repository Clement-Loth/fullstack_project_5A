`ng generate guard` -- generate guard (ie : redirect to login page if not logged)

France connect -- allow openidconnect

# TypeScript

TypeScript might consider certain variables/const to be null, and so refuse to let us interact with their properties (i.e: if said var was get with `document.getElementById()` but returned `null` because not found).

Two way of getting around :   
+ Add ! after the var name (i.e: `foo!.innerHTML`) forces var in the assertion to be non-null => Non-null assertion.
+ Add ? after the var name (i.e : `foo?.innerHTML`) indicates var might be null. In some cases, especially if you want to insert it in the DOM, you'll have to use the `*ngIf` directive.