import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { CommercePlatformTestModule } from '../../../test.module';
import { ProduitDetailComponent } from 'app/entities/produit/produit-detail.component';
import { Produit } from 'app/shared/model/produit.model';

describe('Component Tests', () => {
  describe('Produit Management Detail Component', () => {
    let comp: ProduitDetailComponent;
    let fixture: ComponentFixture<ProduitDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ produit: new Produit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CommercePlatformTestModule],
        declarations: [ProduitDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProduitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProduitDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load produit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.produit).toEqual(jasmine.objectContaining({ id: 123 }));
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
