import { Directive } from '@angular/core';

@Directive({
selector: '[chat]'
})

export class Chat {
  public id;
  public partie;
  public joueur;
  // public fournisseur;
  constructor(public contenu?: string){ }
}
