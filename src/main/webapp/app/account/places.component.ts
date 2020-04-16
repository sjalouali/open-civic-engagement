import { AfterViewInit, Component, EventEmitter, Input, OnDestroy, Output, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import places from 'places.js';

@Component({
  selector: 'jhi-app-places',
  template: `
    <input #input type="search" placeholder="Rechercher une adresse ..." />
  `
})
export class PlacesComponent implements AfterViewInit, OnDestroy, OnChanges {
  private instance!: any;
  @ViewChild('input') input: any;
  @Output() onChange? = new EventEmitter();
  @Output() onClear? = new EventEmitter();

  @Input() type!: string; // type is available as a prop

  ngAfterViewInit(): void {
    this.instance = places({
      appId: 'plDGMWEVL0R1',
      apiKey: 'b11b8aeedc2a949a5b0adc49fe7353d6',
      container: this.input.nativeElement,
      type: 'address' // this.type // we pass the type prop to the instance
      // other places options
    });
    this.instance.on('change', (e: any) => {
      if (this.onChange) {
        this.onChange.emit(e);
      }
    });
    this.instance.on('clear', (e: any) => {
      if (this.onClear) {
        this.onClear.emit(e);
      }
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.type && this.instance) {
      this.instance.configure({
        type: changes.type.currentValue
      });
    }
  }

  ngOnDestroy(): void {
    this.instance.removeAllListeners('change');
    this.instance.removeAllListeners('clear');
    this.instance.destroy();
  }
}
