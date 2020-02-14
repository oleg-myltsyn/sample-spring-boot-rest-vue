import {shallowMount} from '@vue/test-utils'
import Header from '../components/Header.vue'

describe('Header', () => {

    const wrapper = shallowMount(Header, {
        propsData: {msg: 'test'},
        stubs: ['router-link']
    });

    it('renders the correct markup', () => {
        expect(wrapper.html()).toContain('<h1>test</h1>')
    });

});
