import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { AppFileOceDetailComponent } from 'app/entities/app-file-oce/app-file-oce-detail.component';
import { AppFileOce } from 'app/shared/model/app-file-oce.model';

describe('Component Tests', () => {
  describe('AppFileOce Management Detail Component', () => {
    let comp: AppFileOceDetailComponent;
    let fixture: ComponentFixture<AppFileOceDetailComponent>;
    const route = ({ data: of({ appFile: new AppFileOce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [AppFileOceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AppFileOceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AppFileOceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load appFile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.appFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
