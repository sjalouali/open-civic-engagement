import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { ActionServiceOceDetailComponent } from 'app/entities/action-service-oce/action-service-oce-detail.component';
import { ActionServiceOce } from 'app/shared/model/action-service-oce.model';

describe('Component Tests', () => {
  describe('ActionServiceOce Management Detail Component', () => {
    let comp: ActionServiceOceDetailComponent;
    let fixture: ComponentFixture<ActionServiceOceDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ actionService: new ActionServiceOce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [ActionServiceOceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ActionServiceOceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ActionServiceOceDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load actionService on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.actionService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
