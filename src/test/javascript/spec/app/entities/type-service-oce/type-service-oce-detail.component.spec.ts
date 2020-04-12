import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { TypeServiceOceDetailComponent } from 'app/entities/type-service-oce/type-service-oce-detail.component';
import { TypeServiceOce } from 'app/shared/model/type-service-oce.model';

describe('Component Tests', () => {
  describe('TypeServiceOce Management Detail Component', () => {
    let comp: TypeServiceOceDetailComponent;
    let fixture: ComponentFixture<TypeServiceOceDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ typeService: new TypeServiceOce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [TypeServiceOceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TypeServiceOceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeServiceOceDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load typeService on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeService).toEqual(jasmine.objectContaining({ id: 123 }));
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
